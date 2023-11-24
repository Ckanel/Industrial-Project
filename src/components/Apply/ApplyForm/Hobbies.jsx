import React from "react";
import { useState } from "react";
import ApplyInput from "../ApplyInput";
import Button from "../CalendarButton";

function Hobbies() {
    const [hobbies, setHobbies] = useState("");
    const [achievements, setAchievements] = useState("");


    return (
        <>
            <div className="flex flex-col p-4 gap-4 w-1/2 mx-auto">
                <ApplyInput
                    name="Hobbies"
                    setName={setHobbies}
                    placeholder="Mention 1-2 hobbies that are relevant to the industry you wish to work for or offer soft skills that are of relevance e.g. team work, communication, organization skills. (optional)"
                    type="text"
                    id="hobbies"
                />
                <ApplyInput
                    name="Achievements"
                    setName={setAchievements}
                    placeholder="Describe your role and actions, in context of your achievements. (optional)"
                    type="text"
                    id="achievements"
                />
                <Button 
                buttonName={"Add"}
                />
            </div>
        </>
    )

}

export default Hobbies;
