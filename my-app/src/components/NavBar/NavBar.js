import React from 'react';
import { Link } from "react-router-dom";
import  "./NavBar.css";
import logo from './log1.svg';

const NavBar = () => {

    return(
        <nav className="navbar">
            <div><Link to="/" className="home"><img src={logo} alt="Logo" /></Link></div>
            <ul className="nav-links">
                <Link to="/train" className="train">
                    <li>Trenuj</li>
                </Link>
                <Link to="/care" className="care">
                    <li>Opieka</li>
                </Link>
                <Link to="/login" className="login">
                    <li>ZALOGUJ / ZAŁÓŻ KONTO</li>
                </Link>
            </ul>
        </nav>
    )

}
export default NavBar