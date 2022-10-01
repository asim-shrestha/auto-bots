import {FunctionComponent} from "react";
import UnitModel from "../models/UnitModel";
import ClassName from "../proptypes/ClassName";

type UnitCardType = {
	unit: UnitModel
}  & ClassName
const UnitCard: FunctionComponent<UnitCardType> = ({unit, className}) => {
	return (
		<div className={"flex flex-col w-48 p-5 opacity-80 rounded-xl shadow-2xl bg-slate-600 items-center justify-center text-white " + className} >
			<img className="rounded-full w-20 h-20 bg-white" src={unit.src} alt="robot"/>
			<p className="font-bold capitalize text-lg">{unit.name}</p>
			<p>Test</p>
			<p>Effect: Hello!</p>
		</div>
	)
}

export default UnitCard