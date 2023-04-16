import React from 'react'
import Navbar from './Navbar'
import "./common.css"
const Landing = () => {
    return (
        <div className='box'>
            <Navbar></Navbar>
            <h1 className='landing'>Welcome to LMS</h1>
            <h2 style={{ color: "#6f7070" }}>The Superior Loan Management System</h2>
        </div>
    )
}

export default Landing
