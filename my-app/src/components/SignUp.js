import {useEffect, useState} from "react";
import {Col, Container, Row} from "react-bootstrap";
import {Link, useNavigate} from "react-router-dom";
import axios from "axios";
import 'animate.css';
import login from '../assets/video/login.mp4'

export const SignUp = () => {
    const navigate = useNavigate();
    const [loopNum, setLoopNum] = useState(0);
    const [isDeleting, setIsDeleting] = useState(false);
    const [text, setText] = useState('');
    const [delta, setDelta] = useState(300 - Math.random() * 100);
    const [index, setIndex] = useState(1);
    const toRotate = ["przy", "psyjaciela"];
    const period = 1000;

    const [registrationMessage, setRegistrationMessage] = useState("");
    const [form, setForm] = useState({
        name: "",
        email: "",
        password: "",
        passwordConfirmation: ""
    });
    const {name, email, password, passwordConfirmation} = form;

    useEffect(() => {
        let ticker = setInterval(() => {
            tick();
        }, delta);

        return () => { clearInterval(ticker) };
    }, [text])

    const tick = () => {
        let i = loopNum % toRotate.length;
        let fullText = toRotate[i];
        let updatedText = isDeleting ? fullText.substring(0, text.length - 1) : fullText.substring(0, text.length + 1);

        setText(updatedText);

        if (isDeleting) {
            setDelta(prevDelta => prevDelta / 2);
        }

        if (!isDeleting && updatedText === fullText) {
            setIsDeleting(true);
            setIndex(prevIndex => prevIndex - 1);
            setDelta(period);
        } else if (isDeleting && updatedText === '') {
            setIsDeleting(false);
            setLoopNum(loopNum + 1);
            setIndex(1);
            setDelta(500);
        } else {
            setIndex(prevIndex => prevIndex + 1);
        }
    }

    function handleChange(e) {
        setForm({...form, [e.target.name]: e.target.value});
    }

    function handleSubmit(e) {
        e.preventDefault();
        axios
            .post(
                "http://localhost:9090/users",
                {
                    name: name,
                    email: email,
                    password: password,
                },
                {withCredentials: true}
            )
            .then((response) => {
                console.log("registration res", response);
            })
            .catch((error) => {
                console.log("registration error", error);
                setRegistrationMessage("registration error");
            });
        setRegistrationMessage("registered successfully");
        navigate("/login");
        setForm({
            name: "",
            email: "",
            password: "",
            passwordConfirmation: "",
            registrationMessage: "",
        });
    }

    return (
        <section className="signup" id="signup">
            <Container>
                <div className='signup'>
                    <video src={login} autoPlay loop muted/>
                </div>
                <Row className="aligh-items-center">
                    <Col xs={12} md={6} xl={7}>
                        <form className="Auth-form-content" onSubmit={handleSubmit}>
                            <h3 className="Auth-form-title">ZAŁÓŻ KONTO</h3>
                            <div className="form-group mt-3">
                                <input
                                    type="text"
                                    className="form-control mt-1"
                                    placeholder="twoje imię i nazwisko"
                                    name="name"
                                    value={name}
                                    onChange={handleChange}
                                    required
                                />
                                <p><label>IMIĘ I NAZWISKO</label></p>
                            </div>
                            <div className="form-group mt-3">
                                <input
                                    type="email"
                                    className="form-control mt-1"
                                    placeholder="twój mail"
                                    name="email"
                                    value={email}
                                    onChange={handleChange}
                                    required
                                />
                                <p><label>E-MAIL</label></p>
                            </div>
                            <div className="form-group mt-3">
                                <input
                                    type="password"
                                    className="form-control mt-1"
                                    placeholder="twoje hasło"
                                    name="password"
                                    value={password}
                                    onChange={handleChange}
                                    required
                                />
                                <p><label>HASŁO</label></p>
                            </div>
                            <div className="form-group mt-3">
                                <input
                                    type="password"
                                    className="form-control mt-1"
                                    placeholder="potwierdź hasło"
                                    name="passwordConfirmation"
                                    value={passwordConfirmation}
                                    onChange={handleChange}
                                    required
                                />
                                <p><label>POTWIERDŹ HASŁO</label></p>
                            </div>
                            <div className="d-grid gap-2 mt-3">
                                <button type="submit" className="btn btn-primary">
                                    ZAREJESTRUJ
                                </button>
                            </div>

                        </form>
                        <div><h4>{registrationMessage}</h4></div>
                        <h2><Link to="/login">MASZ KONTO? ZALOGUJ SIĘ</Link></h2>
                    </Col>
                </Row>
            </Container>
        </section>
    )
}

export default SignUp
