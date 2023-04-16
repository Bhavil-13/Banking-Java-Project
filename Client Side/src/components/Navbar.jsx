import React from 'react'
import { useNavigate } from 'react-router-dom'

const Navbar = () => {
    const navigate = useNavigate();
    
    return (
        <div>
            <nav className="navbar navbar-light bg-light">

                <a className="navbar-brand mb-0 h1" onClick={() => { navigate("/") }}>LMS</a>
                <a className="navbar-brand" onClick={() => { navigate("/login") }}>Login</a>
                <a className="navbar-brand" onClick={() => { navigate("/register") }}>Register</a>
                {/* <a className="navbar-brand" onClick={() => { navigate("/apply") }}>Apply</a> */}
                <a className="navbar-brand" onClick={() => { navigate("/status") }}>Status</a>

            </nav>
        </div>
    )
}

export default Navbar
