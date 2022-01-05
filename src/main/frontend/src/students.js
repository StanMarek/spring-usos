import axios from 'axios';
import { Student } from './student';
import { useState, useEffect } from 'react';

export const StudentList = () => {
  const [students, setStudentProfile] = useState([]);

  const fetchStudents = () => {
    axios.get('http://localhost:9090/api/students').then((res) => {
      console.log(res);
      setStudentProfile(res.data);
    });
  };

  useEffect(() => {
    fetchStudents();
  }, []);

  return (
    <section className="studentlist">
      {students.map((student) => {
        return <Student key={student.id} student={student}></Student>;
      })}
    </section>
  );
};
