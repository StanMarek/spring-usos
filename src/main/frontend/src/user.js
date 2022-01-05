import axios from 'axios';
import { useEffect, useState } from 'react';

export const User = (props) => {
  const { firstname, lastname, role, email } = props.user;

  // const [isHovering, setIsHovering] = useState(false);
  // const handleMouseOver = () => {
  //   setIsHovering(true);
  // };

  // const handleMouseOut = () => {
  //   setIsHovering(false);
  // };
  return (
    <article
      className="user"
      // onMouseOver={() => {
      //   handleMouseOver;
      // }}
      // onMouseOut={() => {
      //   handleMouseOut;
      // }}
    >
      <h1>
        Full name: {lastname} {firstname}
      </h1>
      <h4>Email: {email}</h4>
      <h4>Role: {userRole(role)}</h4>
      <button className="deleteButton" onClick={() => deleteUser()}>
        Edit user
      </button>
    </article>
  );
};

const deleteUser = () => {
  console.log('Edit');
};

const userRole = (role) => {
  if (role === null || role.name === '') {
    return 'NONE';
  }
  return role.name;
};
