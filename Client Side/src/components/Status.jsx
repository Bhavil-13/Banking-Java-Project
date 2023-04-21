import React, { useState } from 'react'
import Navbar from './Navbar';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import "./common.css";


const Status = () => {
  const navigate = useNavigate();
  const [data, setData] = useState(
    {
      appID: "",
    }
  );

  const handleInput = (e) => {
    const appID = e.target.appID;
    console.log(appID);
    setData({ ...data, [appID]: value });
  }

  const handleSubmit = async e => {
    console.log("YoYO")
    e.preventDefault();
    console.log(
      JSON.stringify({
        appID: data.appID,
      })
    );
    const response = await fetch("http://localhost:5000/", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        appID: data.appID,
      }),
    });
    // const found_data = await axios.post("http://localhost:5000/", data).then(function (response) {
    //   console.log("response found");
    //   console.log(response);
    // }).catch(function (error) {
    //   console.log("error occured in REGISTER");
    // });

    const json = await response.json();
    console.log(json);
    if (!json.success) {
      alert("Enter Valid Application ID");
    }
    if (json.success) {
      localStorage.setItem("application_id", data.appID);
      console.log(localStorage.getItem("application_id"));
      navigate("/shas");
    }
  }

  const onChange = event => {
    setData({ ...data, [event.target.appID]: event.target.value });
  };


  return (
    <div className='form-group box'>
      <Navbar></Navbar>
      <h1> Status </h1>
      <form onSubmit={handleSubmit}>
        <div className='field'>
          <label >ApplicationID</label>
          <input className='form-control' type='text' name='name' onChange={onChange} value={data.appID} defaultValue={"Hello!"} ></input>
        </div>

        <button type='submit' className='btn btn-primary' onClick={() =>navigate('/shas')}>Check</button>
      </form>
    </div>
  )
}

export default Status
