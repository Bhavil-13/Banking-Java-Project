import React, { useState } from 'react'
import Navbar from './Navbar';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import "./common.css";


const Login = () => {
  const navigate = useNavigate();
  const [data, setData] = useState(
    {
      email: "",
      password: "",
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
    const strdata = JSON.stringify(data);


    /* now to do validation wait for backend */
    const found_data = await axios.post("http://localhost:8080/demo/person?requestType=login", strdata).then(function (response) {
      console.log("response found");
      console.log(response.data.Status);
    }).catch(function (error) {
      console.log("error occured in REGISTER");
    });
  }


  return (
    <div className='form-group box'>
      <Navbar></Navbar>
      <h1> Login </h1>
      <form onSubmit={handleSubmit}>
        <div className='field'>
          <label htmlFor='email'>email</label>
          <input className='form-control' type='text' autoComplete='off' name='email' id='email' value={data.email} onChange={handleInput}></input>
        </div>

        <div>
          <label htmlFor='password'>password</label>
          <input className='form-control' type='text' autoComplete='off' name='password' id='password' value={data.password} onChange={handleInput}></input>
        </div>

        <button type='submit' className='btn btn-primary'>Login</button>
        <a className='a'  onClick={()=>navigate("/loginasadmin")}>login as admin?</a>
      </form>
    </div>
  )
}

export default Login
