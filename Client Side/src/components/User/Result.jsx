import React from 'react'
import UserNav from './UserNav'

const Result = (props) => {
  return (
    <div>
      <UserNav></UserNav>
      <h1>{props.msg}</h1>
    </div>
  )
}

export default Result
