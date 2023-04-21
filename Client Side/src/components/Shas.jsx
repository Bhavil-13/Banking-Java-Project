import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import Navbar from './Navbar';
import "./common.css";

function Shas() {
  const navigate = useNavigate();
  const [applicationId, setApplicationId] = useState('');
  const [approvalStatus, setApprovalStatus] = useState(null);

  const handleInput = (e) => {
    const appID = e.target.appID;
    setApplicationId(e.target.value);
  }

  const handleSubmit = async e => {
    e.preventDefault();
    console.log("Submitting form with application ID: ", applicationId);

    // Fetch the loan approval status from your database
    const response = await fetch(`/api/loan/approvalStatus/${applicationId}`)
      .then(res => res.json())
      .catch(error => {
        console.error('Error fetching loan approval status:', error);
      });

    if (!response || !response.approvalStatus) {
      console.log('Loan approval status not found for application ID:', applicationId);
      alert('Loan approval status not found for application ID:', applicationId);
    } else {
      console.log('Loan approval status for application ID', applicationId, 'is', response.approvalStatus);
      setApprovalStatus(response.approvalStatus);
      localStorage.setItem('applicationId', applicationId);
      navigate('/shas');
    }
  }

  return (
    <div className='form-group box'>
      <Navbar />
      <h1>Loan Status</h1>
      <form onSubmit={handleSubmit}>
        <div className='field'>
          <label>Application ID</label>
          <input
            className='form-control'
            type='text'
            name='name'
            onChange={handleInput}
            value={applicationId}
          />
        </div>
        <button type='submit' className='btn btn-primary'>Check</button>
      </form>
      {approvalStatus === 'approved' && <p>The loan has been approved!</p>}
      {approvalStatus === 'denied' && <p>The loan has been denied.</p>}
      {!approvalStatus && <p>Enter an application ID to check the status.</p>}
    </div>
  );
}

export default Shas;