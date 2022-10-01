import {FunctionComponent} from "react";

type ButtonProps = {
	text: string
	src: string
}
const Button: FunctionComponent<ButtonProps> = ({text, src}) => {
	return (
		<button
			className={
				"group rounded-lg py-5 px-7 inline-flex items-center bg-gradient-to-r from-red-600 to-red-400 " +
				"shadow-md shadow-red-200 " +
				"hover:shadow-2xl hover:shadow-red-300 " +
				"active:ring active:ring-red-900"
			}>
			<div className="w-7 h-7 grid place-items-center mr-2">
				<svg className="transition-all ease-in-out fill-current w-5 h-5 group-hover:w-7 group-hover:h-7" xmlns={src} viewBox="0 0 20 20">
					<path d="M13 8V2H7v6H2l8 8 8-8h-5zM0 18h20v2H0v-2z"/>
				</svg>
			</div>
			<span className="text-white text-3xl font-bold">{text}</span>
		</button>
	)
}

export default Button;