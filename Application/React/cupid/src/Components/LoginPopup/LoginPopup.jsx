import '../LoginPopup/LoginPopup.css';
import React, { useState } from 'react';
import axios from 'axios'; 

const LoginPopup = ({ onClose }) => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    const handleEmailChange = (event) => {
        setEmail(event.target.value);
    };

    const handlePasswordChange = (event) => {
        setPassword(event.target.value);
    };

    const handleSubmit = async (event) => {
        event.preventDefault();
        try {
            const response = await axios.get('http://localhost:8080/user/1'
            );
            console.log('Login request submitted:', response.data);
        } catch (error) {
            console.error('Login request failed:', error);
        }
    };

    return (
        <div className='login-popup-wrap'>
            <div className='login-popup'>
                <span onClick={onClose} className='close'></span>
                <h2>Login</h2>
                <form onSubmit={handleSubmit}>
                    <div>
                        <input type="email" placeholder='Your email..' value={email} onChange={handleEmailChange} required />
                    </div>
                    <div>
                        <input type="password" placeholder='Your password..' value={password} onChange={handlePasswordChange} required />
                    </div>
                    <h3 className='errors'></h3>
                    <button className="btn" type="submit">Login</button>
                </form>
            </div>
        </div>
    );
};

export default LoginPopup;
