export const Student = (props) => {
  const { user, id, yearOfBirth, points, status } = props.student;
  const { firstname, lastname, email } = user;
  return (
    <article className="student">
      <h3>Student ID: {id}</h3>
      <h1>
        Full name: {lastname} {firstname}
      </h1>
      <h4>Email: {email}</h4>
      <h4>Current points: {points}</h4>
      <h4>Current status: {status}</h4>
      <h4>Year of birth: {yearOfBirth} </h4>
      <h4></h4>
      <button className="editButton" onClick={() => editStudent()}>
        Edit student
      </button>
      <button className="deleteButton" onClick={() => deleteStudent()}>
        Delete student
      </button>
    </article>
  );
};

const editStudent = () => {};
const deleteStudent = () => {};
 