import Calendar from "react-calendar";
import { useState } from "react";
import Input from "../ApplyInput";
import "react-calendar/dist/Calendar.css";
import Button from "../CalendarButton";

function Volunteering() {
  const [volunteer, setVolunteer] = useState("");
  const [dateRange, setDateRange] = useState([null, null]);
  const [showCalendar, setShowCalendar] = useState(false);

  const handleInputChange = (event) => {
    setVolunteer(event.target.value);
    setShowCalendar(true);
  };

  const handleDateChange = (dateRange) => {
    setDateRange(dateRange);
  };

  return (
    <div>
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
            className={"mx-auto"}
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
      {dateRange[0] && dateRange[1] && (
        <Button buttonName={"Add Volunteer Work/Project"} />
      )}
    </div>
  );
}

export default Volunteering;
