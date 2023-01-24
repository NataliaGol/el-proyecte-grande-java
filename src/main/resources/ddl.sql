------ DELETE EVERYTHING FROM THE DATABASE ------
BEGIN;
DO
$$
    DECLARE
        r RECORD;
    BEGIN
        FOR r IN (SELECT tablename FROM pg_tables WHERE schemaname = 'public')
            LOOP
                EXECUTE 'DROP TABLE ' || r.tablename || ' CASCADE';
            END LOOP;
    END
$$;
COMMIT;
DROP FUNCTION IF EXISTS lock_account_on_failed_attempts();

------ CITIES -----
CREATE TABLE cities
(
    city_id  SERIAL PRIMARY KEY,
    name     VARCHAR(255) NOT NULL,
    postcode VARCHAR(6)   NOT NULL UNIQUE
);
CREATE INDEX idx_cities_postcode ON cities (postcode);
CREATE INDEX idx_cities_name ON cities (name);

------ USER_TYPES -----
CREATE TABLE user_types
(
    user_type_id SERIAL PRIMARY KEY,
    name         VARCHAR(255) NOT NULL UNIQUE
);

------ USERS -----
CREATE TABLE users
(
    user_id                  SERIAL PRIMARY KEY,
    user_name                VARCHAR(255) UNIQUE                          NOT NULL,
    email                    VARCHAR(255) UNIQUE                          NOT NULL,
    password                 VARCHAR(255)                                 NOT NULL,
    profile_picture_location VARCHAR,
    avatar_small_location    VARCHAR,
    user_type_id             INTEGER REFERENCES user_types (user_type_id) NOT NULL,
    city_id                  INTEGER REFERENCES cities (city_id),
    description              VARCHAR(255),
    is_locked                BOOLEAN                  DEFAULT FALSE,
    is_banned                BOOLEAN                  DEFAULT FALSE,
    ban_expiration           TIMESTAMP WITH TIME ZONE DEFAULT NULL,
    is_active                BOOLEAN                  DEFAULT TRUE,
    created_at               TIMESTAMP WITH TIME ZONE DEFAULT NOW()
);
CREATE INDEX idx_users_email ON users (email);
CREATE INDEX idx_users_user_name ON users (user_name);

------ LOGIN_ATTEMPTS -----
CREATE TABLE login_attempts
(
    id         SERIAL PRIMARY KEY,
    successful BOOLEAN,
    ip         VARCHAR(39),
    user_id    INTEGER REFERENCES users (user_id),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT NOW()
);
CREATE INDEX idx_login_attempts_user_id ON login_attempts (user_id);
CREATE INDEX idx_login_attempts_ip ON login_attempts (ip);
CREATE INDEX idx_login_attempts_created_at ON login_attempts (created_at);
CREATE INDEX idx_login_attempts_successful ON login_attempts (successful);

------ lock user account after 3 failed attempts ------
CREATE OR REPLACE FUNCTION lock_account_on_failed_attempts() RETURNS TRIGGER AS
$$
BEGIN
    IF NOT NEW.successful THEN
        UPDATE users
        SET is_locked = true
        WHERE user_id = NEW.user_id
          AND (SELECT COUNT(*) FROM login_attempts WHERE user_id = NEW.user_id AND successful = false) >= 3;
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER lock_account_on_failed_attempts
    AFTER INSERT
    ON login_attempts
    FOR EACH ROW
EXECUTE FUNCTION lock_account_on_failed_attempts();
-------------------------------------------------------

------ BREEDS -----
CREATE TABLE breeds
(
    breed_id    SERIAL PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    description VARCHAR(255)
);

------ ANIMAL_TYPES -----
CREATE TABLE animal_types
(
    animal_type_id SERIAL PRIMARY KEY,
    name           VARCHAR(255) NOT NULL,
    description    VARCHAR(255)
);

------ ANIMALS -----
CREATE TABLE animals
(
    animal_id        SERIAL PRIMARY KEY,
    name             VARCHAR(255) NOT NULL,
    animal_type_id   INTEGER REFERENCES animal_types (animal_type_id),
    breed_id         INTEGER REFERENCES breeds (breed_id),
    user_id          INTEGER REFERENCES users (user_id),
    birth_year       INTEGER      NOT NULL,
    is_male          BOOLEAN      NOT NULL,
    picture_location VARCHAR,
    description      VARCHAR(255)
);
CREATE INDEX idx_animals_user_id ON animals (user_id);

------ TRAINING_LEVELS -----
CREATE TABLE training_levels
(
    training_level_id SERIAL PRIMARY KEY,
    name              VARCHAR(255) NOT NULL,
    description       VARCHAR(255)
);

------ LESSONS -----
CREATE TABLE lessons
(
    lesson_id         SERIAL PRIMARY KEY,
    lesson_name       VARCHAR(255)                                           NOT NULL,
    training_level_id INTEGER REFERENCES training_levels (training_level_id) NOT NULL,
    description       VARCHAR(255),
    image_location    VARCHAR
);

------ LESSON_STEPS -----
CREATE TABLE lesson_steps
(
    lesson_step_id   SERIAL PRIMARY KEY,
    lesson_step_name VARCHAR(255)                           NOT NULL,
    lesson_id        INTEGER REFERENCES lessons (lesson_id) NOT NULL,
    step_number      INTEGER                                NOT NULL,
    description      VARCHAR
);
CREATE INDEX idx_lesson_steps_lesson_id ON lesson_steps (lesson_id);

------ LESSONS_ANIMALS -----
CREATE TABLE lessons_animals
(
    animal_id INTEGER REFERENCES animals (animal_id),
    lesson_id INTEGER REFERENCES lessons (lesson_id) NOT NULL,
    progress  INTEGER DEFAULT 0,
    finished  BOOLEAN DEFAULT FALSE,
    PRIMARY KEY (animal_id, lesson_id),
    UNIQUE (animal_id, lesson_id)
);
CREATE INDEX idx_lessons_animals_animal_id_lesson_id ON lessons_animals (animal_id, lesson_id);

------ TAGS -----
CREATE TABLE tags
(
    tag_name VARCHAR(50) UNIQUE PRIMARY KEY
);

------ CARE_ANNOUNCEMENT_TYPES -----
CREATE TABLE care_announcement_types
(
    care_announcement_type_id SERIAL PRIMARY KEY,
    name                      VARCHAR(255) NOT NULL UNIQUE
);

------ TIME_UNIT ----- (HOUR, DAY, ...)
CREATE TABLE time_unit
(
    time_unit_id SERIAL PRIMARY KEY,
    name         VARCHAR(10) NOT NULL UNIQUE
);

------ CARE_ANNOUNCEMENTS -----
CREATE TABLE care_announcements
(
    care_announcement_id      SERIAL PRIMARY KEY,
    user_id                   INTEGER REFERENCES users (user_id)                                     NOT NULL,
    care_announcement_type_id INTEGER REFERENCES care_announcement_types (care_announcement_type_id) NOT NULL,
    title                     VARCHAR(255)                                                           NOT NULL,
    description               VARCHAR(255)                                                           NOT NULL,
    city_id                   INTEGER REFERENCES cities (city_id)                                    NOT NULL,
    price                     NUMERIC                                                                NOT NULL,
    time_unit_id              INTEGER REFERENCES time_unit (time_unit_id)                            NOT NULL
);

------ CARE_ANNOUNCEMENTS_TAGS -----
CREATE TABLE care_announcements_tags
(
    care_announcement_id INTEGER REFERENCES care_announcements (care_announcement_id),
    tag_name             VARCHAR(50) REFERENCES tags (tag_name),
    PRIMARY KEY (care_announcement_id, tag_name),
    UNIQUE (care_announcement_id, tag_name)
);
CREATE INDEX idx_care_announcements_tags_care_announcement_id_tag_name ON care_announcements_tags (care_announcement_id, tag_name);

------ REVIEWS -----
CREATE TABLE reviews
(
    review_id            SERIAL PRIMARY KEY,
    care_announcement_id INTEGER REFERENCES care_announcements (care_announcement_id) NOT NULL,
    user_id              INTEGER REFERENCES users (user_id)                           NOT NULL,
    description          VARCHAR(255)                                                 NOT NULL,
    stars                INTEGER                                                      NOT NULL
);
CREATE INDEX idx_reviews_care_announcement_id ON reviews (care_announcement_id);

------ CONVERSATIONS -----
CREATE TABLE conversations
(
    conversation_id      SERIAL PRIMARY KEY,
    original_sender_id   INTEGER REFERENCES users (user_id) NOT NULL,
    original_receiver_id INTEGER REFERENCES users (user_id) NOT NULL,
    care_announcement_id INTEGER REFERENCES care_announcements (care_announcement_id)
);
CREATE INDEX idx_conversations_sender_id ON conversations (original_sender_id);
CREATE INDEX idx_conversations_receiver_id ON conversations (original_receiver_id);

------ MESSAGES -----
CREATE TABLE messages
(
    message_id      SERIAL PRIMARY KEY,
    created_at      TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    conversation_id INTEGER REFERENCES conversations (conversation_id) NOT NULL,
    sender_id       INTEGER REFERENCES users (user_id)                 NOT NULL,
    receiver_id     INTEGER REFERENCES users (user_id)                 NOT NULL,
    message_content VARCHAR(255)                                       NOT NULL
);
CREATE INDEX idx_messages_conversation_id ON messages (conversation_id);
CREATE INDEX idx_messages_created_at ON messages (created_at);
