import { useForm } from "react-hook-form"
import { Link, useNavigate } from "react-router-dom"
import { yupResolver } from "@hookform/resolvers/yup"
import * as yup from "yup"
import { useSelector, useDispatch } from "react-redux"
import { RootState, AppDispatch } from "../store"
import { login } from "../store/authSlice"
import { useEffect } from "react"

export type LoginFormData = {
    email: string,
    password: string
}

const schema = yup
    .object({
        email: yup.string().email().required("Email is required."),
        password: yup.string().required("Password is required.")
    })
    .required()

const Login = () => {
    const { register, handleSubmit, formState: { errors } } = useForm<LoginFormData>({
        resolver: yupResolver(schema)
    })

    const { loading, userToken, error, success } = useSelector(
        (state: RootState) => state.auth
    )

    const dispatch = useDispatch<AppDispatch>()
    
    const navigate = useNavigate()

    const onSubmit = (data: LoginFormData) => {
       dispatch(login(data))
    }

    useEffect(() => {
        if (userToken) navigate('/profile')
    }, [navigate, userToken, success])
    
    return (
        <>
        <div className="container-sm container-login border rounded mt-5">
            <form className="m-2" onSubmit={handleSubmit(onSubmit)}>
                <h3 className="text-center">Login</h3>
                <hr/>
                <div className="row mx-auto form-outline mb-3">
                    <div className="col-12">
                        <input type="email" className="form-control" placeholder="Email address..." {...register("email")}/>
                        { errors.email && <small style={{ color: "red" }}>{errors.email.message}</small> }
                    </div>
                </div>
                <div className="row mx-auto form-outline mb-3">
                    <div className="col-12">
                        <input type="password" className="form-control" placeholder="Password..." {...register("password")}/>
                        { errors.password && <small style={{ color: "red" }}>{errors.password.message}</small> }
                    </div>
                </div>
                <div className="row mx-auto form-outline mb-3">
                    <div className="col-12">
                        <button type="submit" className="btn btn-primary" disabled={loading}>
                            { loading ? 'Submitting...' : 'Sign In' }
                        </button>
                    </div>
                </div>
                <div className="row mx-auto form-outline mb-3">
                    <div className="col-12 text-center">Not a member? <Link to="/registration">Register</Link></div>
                </div>
            </form>
        </div>
        {
            error &&
            <div className="container-sm container-login mt-5">
                <div className="alert alert-danger" role="alert">
                    <p>{(error === "Access Denied")? "Wrong credentials, please try again" : error}</p>
                </div>
            </div>
        }
        
        </>
    )
}

export default Login