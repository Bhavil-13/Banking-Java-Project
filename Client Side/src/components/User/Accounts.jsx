import React from 'react'
import UserNav from './UserNav'
import { useNavigate } from 'react-router-dom'
import Result from './Result';

const Accounts = () => {
    const navigate = useNavigate();

    const getAccounts = () =>
    {
        console.log("getAccounts method");
        const personid = localStorage.getItem("personid");
        if(personid !== null)
        {
            /* get the list from the backend  and render it*/
        }
        else
        {
            navigate("/login");
        }
    }
  return (
    <div>
      <UserNav></UserNav>
      <div className='box'>
        Make a new Account
        <button className='btn btn-primary'>yes</button>
        {/* <Result msg="hello"></Result> */}
      </div>
    </div>
  )
}

export default Accounts
Accounts