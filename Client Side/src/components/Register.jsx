import React, { useState } from 'react'
import Navbar from './Navbar';
import axios from 'axios';

const Register = () => {

    const [data, setData] = useState(
        {
            name: "",
            address: "",
            email: "",
            password: ""
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

        console.log(data.name, data.address, data.email, data.password);
        const strdata = JSON.stringify(data);
        console.log(strdata);
        /* now to do validation wait for backend */
        const found_data = await axios.post("http://localhost:8080/demo/person?requestType=login", strdata).then(function (response) {
            console.log("response found");
            console.log(response);
        }).catch(function (error) {
                console.log("error occured in REGISTER");
        });

        /* personid will be return and will be cached */
    }
    return (
        <div className='form-group box'>
            <Navbar></Navbar>
            <h1> Register </h1>
            <form onSubmit={handleSubmit}>

                <div className='field'>
                    <label htmlFor='name'>name</label>
                    <input className='form-control' type='text' autoComplete='off' name='name' id='name' value={data.name} onChange={handleInput}></input>
                </div>

                <div className='field'>
                    <label htmlFor='address'>address</label>
                    <input className='form-control' type='text' autoComplete='off' name='address' id='address' value={data.address} onChange={handleInput}></input>
                </div>

                <div className='field'>
                    <label htmlFor='email'>email</label>
                    <input className='form-control' type='text' autoComplete='off' name='email' id='email' value={data.email} onChange={handleInput}></input>
                </div>

                <div className='field'>
                    <label htmlFor='password'>password</label>
                    <input className='form-control' type='text' autoComplete='off' name='password' id='password' value={data.password} onChange={handleInput}></input>
                </div>

                <button type='submit' className='btn btn-primary'>Register</button>
            </form>

        </div>
    )
}

export default Register
