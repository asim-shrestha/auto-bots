import {FunctionComponent} from "react";
import UnitModel from "../models/UnitModel";
import UnitCard from "./UnitCard";

const Team: FunctionComponent = () => {
	const team: UnitModel[] = [
		{id: "1", name: "person", src:"/bot.svg"},
		{id: "2", name: "test", src:"/bot.svg"},
		{id: "3", name: "hello", src:"/bot.svg"},
		{id: "4", name: "sdsad", src:"/bot.svg"},
		{id: "5", name: "sdad", src:"/bot.svg"},
	]

	return (
		<div>
			<div className="flex flex-row gap-4 static">
				{ team.map((unit) => <Unit key={unit.id} unit={unit}/> )}
			</div>
		</div>
	)
}

/*
 * Unit image along with shadow
 */
type UnitType = {
	unit: UnitModel
}
const Unit: FunctionComponent<UnitType> = ({unit}) => {
	console.log(unit.src)
	console.log(unit)
	return (
		<div className="group relative">
			<img className="pb-7 " src={unit.src} alt="robot"/>
			<div className="absolute bottom-0 bg-slate-500 blur h-12 w-20 -z-10"/>
			<UnitCard className="group-hover:block hidden absolute bottom-1/2 left-full" unit={unit}/>
		</div>
	)
}

export default Team;