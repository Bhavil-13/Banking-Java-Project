import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import Navbar from './Navbar';
import "./common.css";

function Shas() {
  const navigate = useNavigate();
  const [application_id, setApplicationId] = useState('');
  const [approvalStatus, setApprovalStatus] = useState(null);

  const handleInput = (e) => {
    const appID = e.target.appID;
    setApplicationId(e.target.value);
  }

  const handleSubmit = async e => {
    e.preventDefault();
    console.log("Submitting form with application ID: ", application_id);

    // Fetch the loan approval status from your database
    const response = await fetch(`status/${application_id}`)
      .then(res => res.json())
      .catch(error => {
        console.error('Error fetching loan approval status:', error);
      });

    if (!response || !response.approvalStatus) {
      console.log('Loan approval status not found for application ID:', application_id);
      alert('Loan approval status not found for application ID:', application_id);
    } else {
      console.log('Loan approval status for application ID', application_id, 'is', response.approvalStatus);
      setApprovalStatus(response.approvalStatus);
      localStorage.setItem('applicationId', application_id);
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
            value={application_id}
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
