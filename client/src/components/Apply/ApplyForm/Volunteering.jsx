import Calendar from "react-calendar";
import { useState } from "react";
import Input from "../ApplyInput";
import "react-calendar/dist/Calendar.css";
import Button from "../CalendarButton";
import axios from "axios";
import Popup from "../../Popup/Popup";

function Volunteering() {
  const [volunteer, setVolunteer] = useState("");
  const [dateRange, setDateRange] = useState([null, null]);
  const [showCalendar, setShowCalendar] = useState(false);
  const seminarId = localStorage.getItem("seminar");

  const startDate = dateRange[0];
  const endDate = dateRange[1];
  const handleInputChange = (event) => {
    setVolunteer(event.target.value);
    if (event.target.value.length > 0) {
      setShowCalendar(true);
    } else {
      setShowCalendar(false);
    }
  };

  const handleDateChange = (dateRange) => {
    setDateRange(dateRange);
  };

  const handleVolunteering = async (e) => {
    e.preventDefault();
    try {      
      const response = await axios.post(
        "http://localhost:8080/api/volunteering",
        {
          volunteer,
          startDate,
          endDate,
          seminarId,
        }
      );
      Popup({
        title: "Success!",
        text: "You have successfully added your volunteering work!",
        icon: "success",
        timer: 1500,
        showConfirmButton: false,
      });
    } catch (error) {
      Popup({
        title: "Error!",
        text: "There was an error adding your volunteering work.",
        icon: "error",
        timer: 1500,
        showConfirmButton: false,
      });
    }
  };

  return (
    <div>
      <div className="w-3/4 mx-auto">
        <h2 className="text-xl font-bold text-center mt-8 mb-4">
          Please enter any project or volunteering work you may have done.
          (optional)
        </h2>
        <Input
          name="volunteer"
          setName={setVolunteer}
          placeholder="Enter any project or volunteering work you may have done. (optional)"
          handleWorkChange={handleInputChange}
          type="text"
          id="volunteer"
        />
        {showCalendar && (
          <div>
            <h2 className="text-xl font-bold text-center mt-8 mb-4">
              Please select the date range for which you participated:
            </h2>
            <Calendar
              selectRange
              minDetail="year"
              maxDetail="month"
              next2Label={null}
              prev2Label={null}
              nextLabel={null}
              prevLabel={null}
              className={"mx-auto my-calendar"}
              formatShortWeekday={(locale, value) => {
                if (window.innerWidth < 640) {
                  return ["S", "M", "T", "W", "T", "F", "S"][value.getDay()];
                } else {
                  return ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"][
                    value.getDay()
                  ];
                }
              }}
              value={dateRange}
              onChange={handleDateChange}
            />
          </div>
        )}
        {startDate && endDate && (
          <Button onClick={handleVolunteering} buttonName={"Add"} />
        )}
                <div className="flex flex-row justify-center items-center md:gap-16 gap-4">
        <Button
          id="submit"
          className=""
          buttonName={"Submit"}
          onClick={() => localStorage.removeItem("seminar") && navigate("/")}
        />
        <Button
          id="delete"
          className=""
          buttonName={"Cancel"}
          onClick={() => localStorage.removeItem("seminar") && navigate("/")}
        />
        </div>
      </div>
    </div>
  );
}

export default Volunteering;
