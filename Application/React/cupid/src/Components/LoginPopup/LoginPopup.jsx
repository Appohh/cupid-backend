import '../LoginPopup/LoginPopup.css';
import React, { useState } from 'react';
import axios from 'axios';
import UserService from '../../Services/UserService';


const LoginPopup = ({ onClose }) => {

    const Authenticate = () => {
        UserService.authenticateUser(formData)
        .then(data => OnSucces(data))
        .catch(error => console.log('Authentication failed:', error));
    }

    const OnSucces = (data) => {
        window.location.href("/foryou")
        console.log('Authentication successful:', data)

    }

    const [formData, setFormData] = useState({
        email: '',
        password: ''
    });

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData(prevFormData => ({
            ...prevFormData,
            [name]: value,
        }));
    };
    

    const handleSubmit = (e) => {
        e.preventDefault();
        console.log('Form data submitted:', formData);
        Authenticate();
    };

    return (
        <div className='login-popup-wrap'>
            <div className='login-popup'>
                <span onClick={onClose} className='close'></span>
                <h2>Login</h2>
                <form onSubmit={handleSubmit}>
                    <div>
                        <input name='email' type="email" placeholder='Your email..' onChange={handleChange} required />
                    </div>
                    <div>
                        <input name='password' type="password" placeholder='Your password..' onChange={handleChange} required />
                    </div>
                    <h3 className='errors'></h3>
                    <button className="btn" type="submit">Login</button>
                </form>
            </div>
        </div>
    );
};

export default LoginPopup;
