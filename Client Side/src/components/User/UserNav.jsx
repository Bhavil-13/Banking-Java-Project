import React from 'react'
import { useNavigate } from 'react-router-dom';
const UserNav = () => {
    const navigate = useNavigate();
    const logOut = () => {
        localStorage.setItem("username", "");
        localStorage.setItem("password", "");
        localStorage.setItem("admin", false);
        localStorage.setItem("personid","");
        navigate("/");
    }
  return (
    <div>
          <div>
              <nav className="navbar navbar-light bg-light">

                  <a className="navbar-brand mb-0 h1" onClick={() => { navigate("/") }}>LMS</a>
                  {/* <a className="navbar-brand" onClick={() => { navigate("/login") }}>Login</a> */}
                  {/* <a className="navbar-brand" onClick={() => { navigate("/register") }}>Register</a> */}
                  <a className="navbar-brand" onClick={() => { navigate("/accounts") }}>Accounts</a>
                  {/* getAccounts, makeAccount -> personid, CSdetails send to login */}
                  <a className="navbar-brand" onClick={() => { navigate("/eligibility") }}>CheckEligibility</a>
                  {/* checkElgibility , personid, amount, => always return true */}
                  <a className="navbar-brand" onClick={() => { navigate("/apply") }}>Apply</a>
                  <a className="navbar-brand" onClick={() => { navigate("/repay") }}>RepayLoan</a>
                  <a className="navbar-brand" onClick={() => { navigate("/transaction") }}>Transaction</a>
                  <a className="navbar-brand" onClick={() => { logOut(); }}>Logout</a>

              </nav>
          </div>
    </div>
  )
}

export default UserNav
