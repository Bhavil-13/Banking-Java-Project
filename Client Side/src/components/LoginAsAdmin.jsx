import React, { useState } from 'react'
import Navbar from './Navbar';
import axios from 'axios';

const LoginAsAdmin = () => {

    const [data, setData] = useState(
        {
            email: "",
            password: "",
            admin: true
        }
    );
    const handleInput = (e) => {
        const name = e.target.name;
        const value = e.target.value;
        console.log(name, value);
        setData({ ...data, [name]: value });
    }
    const handleSubmit = async (e) => {
        e.preventDefault();

        console.log(data.email, data.password);

        /* now to do validation wait for backend */
        const found_data = await axios.post("http://localhost:5000/", data).then(function (response) {
            console.log("response found");
            console.log(response);
        }).catch(function (error) {
            console.log("error occured in REGISTER");
        });

        // if login succesfull then add to local storage

    }


    return (
        <div className='form-group box'>
            <Navbar></Navbar>
            <h1> Login as Admin </h1>
            <form onSubmit={handleSubmit}>
                <div className='field'>
                    <label htmlFor='email'>email</label>
                    <input className='form-control' type='text' autoComplete='off' name='email' id='email' value={data.email} onChange={handleInput}></input>
                </div>

                <div>
                    <label htmlFor='password'>password</label>
                    <input className='form-control' type='text' autoComplete='off' name='password' id='password' value={data.password} onChange={handleInput}></input>
                </div>
                <button type='submit' className='btn btn-primary'>login as admin</button>
            </form>
        </div>
    )
}

export default LoginAsAdmin
