import type {NextPage} from 'next'
import Team from "../components/team";
import Button from "../components/Button";
import {useState} from "react";

const Home: NextPage = () => {
    const [shop, setShop] = useState();
    const resetShop = () => {

    }
	return (
		<div>
			<div className="grid w-screen h-screen place-items-center">
				<Team/>
				<Button text="Reset" src="http://www.w3.org/2000/svg"/>
				<Button text="Play" src="http://www.w3.org/2000/svg"/>
			</div>
		</div>
	)
}

export default Home
