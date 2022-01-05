import axios from 'axios';
import { useEffect, useState } from 'react';
import { User } from './user';

export const UserList = () => {
  const [users, setUserProfile] = useState([]);

  const fetchUsers = () => {
    axios.get('http://localhost:9090/api/users').then((res) => {
      console.log(res);
      setUserProfile(res.data);
    });
  };

  useEffect(() => {
    fetchUsers();
  }, []);

  return (
    <section className="userlist">
      {users.map((user) => {
        return <User key={user.email} user={user}></User>;
      })}
    </section>
  );
};
