import React from 'react'
import { useNavigate } from 'react-router-dom';
import UserNav from './User/UserNav';
const UserDashboard = () => {
    const navigate = useNavigate();
    const logOut = () => {
        localStorage.setItem("username", "");
        localStorage.setItem("password", "");
        localStorage.setItem("admin", false);
        navigate("/");
    }
    return (
        <UserNav></UserNav>
    )
}

export default UserDashboard
