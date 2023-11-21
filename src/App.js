import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Register from "./components/Register/Register";
import Login from "./components/Login/Login";
import Logout from "./components/Logout/Logout";
import Apply from "./components/Apply/Apply";
function App() {
  return (
    <Router>
    <Routes>
      <Route path="/Register" element={<Register />} />
      <Route path="/Login" element={<Login />} />
      <Route path="/Logout" element={<Logout />} />
      <Route path="/Apply" element={<Apply />} />
    </Routes>
  </Router>
  );
}

export default App;
