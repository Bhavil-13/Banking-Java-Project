import { useNavigate } from "react-router-dom";
const navigate = useNavigate();

export default Validate = () => {
    const userName = localStorage.getItem("username");
    const password = localStorage.getItem("password");
    const isAdmin = localStorage.getItem("isAdmin");
    
    if(userName === "") {
        navigate("/login");
    }        
    /* some validation logic */
    flag = false;
    if(flag)
        return true;
    
}