import React from 'react'
import { useEffect } from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';

// importing pages
import Landing from './components/Landing';
import Login from   './components/Login';
import LoginAsAdmin from   './components/LoginAsAdmin';
import Register from   './components/Register';
import UserDashboard from './components/UserDashboard';
import Accounts from './components/User/Accounts';
import EligibilityCheck from "./components/User/EligibilityCheck";
import Transaction from './components/User/Transaction';
import RepayLoan from './components/User/RepayLoan';
import Apply from './components/User/Apply';
import Result from './components/User/Result';

const App = () => {
  // initializing localstorage
  useEffect(() => {
    localStorage.setItem("username", "");
    localStorage.setItem("password", "");
    localStorage.setItem("admin", false);
    localStorage.setItem("personid", "");
    localStorage.setItem("msg", "dummy");
    console.log("Initialized the Local Storage!!");
  }, []);

  const getLS = () => {
    console.log("TESTING LOCAL STORAGE RETRIEVAL");
    console.log(localStorage.getItem("username"));
    console.log(localStorage.getItem("password"));
    console.log(localStorage.getItem("admin"));
  }
  // the main part
  return (
    <>
      {/* App
      <button onClick={() => getLS()}> LS </button> */}
      <BrowserRouter>
        <Routes>
          <Route index element={<Landing />}></Route>
          <Route path='/login' element={<Login/>}></Route>
          <Route path='/loginasadmin' element={<LoginAsAdmin/>}></Route>
          <Route path='/register' element={<Register/>}></Route>
          <Route path='/userdashboard' element={<UserDashboard/>}></Route>
          <Route path='/accounts' element={<Accounts/>}></Route>
          <Route path='/eligibility' element={<EligibilityCheck/>}></Route>
          <Route path='/transaction' element={<Transaction/>}></Route>
          <Route path='/repay' element={<RepayLoan/>}></Route>
          <Route path='/apply' element={<Apply/>}></Route>
          <Route path='/result' element={<Result msg={localStorage.getItem("msg")}/>}></Route>
        </Routes>
      </BrowserRouter>
    </>
  )
}

export default App
