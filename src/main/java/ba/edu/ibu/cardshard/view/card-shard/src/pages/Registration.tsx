import { useForm } from "react-hook-form"
import { Link, useNavigate } from "react-router-dom"
import { yupResolver } from "@hookform/resolvers/yup"
import * as yup from "yup"
import { useSelector, useDispatch } from "react-redux"
import { RootState, AppDispatch } from "../store"
import { registerUser } from "../store/authSlice"
import { useEffect } from "react"

export type RegisterFormData = {
    firstName: string,
    lastName: string,
    email: string,
    password: string,
    username: string,
    country: string,
    city: string
}

const schema = yup
    .object({
        firstName: yup.string().required("First name is required."),
        lastName: yup.string().required("Last name is required."),
        email: yup.string().email().required("Email is required."),
        username: yup.string().min(6).max(20).required("username is required."),
        password: yup.string().min(8).required("Password is required."),
        country: yup.string().required("Country is required."),
        city: yup.string().required("City is required.")
    })
    .required()
 

const Registration = () => {
    const { register, handleSubmit, formState: { errors } } = useForm<RegisterFormData>({
        resolver: yupResolver(schema)
    })

    const { loading, userToken, error, success } = useSelector(
        (state: RootState) => state.auth
    )
    
    const dispatch = useDispatch<AppDispatch>()

    const navigate = useNavigate()

    const onSubmit = (data: RegisterFormData) => {
        dispatch(registerUser(data))
    }

    useEffect(() => {
        // Redirect user to login page if registration was successful
        if (success) navigate('/login')
        // Redirect authenticated user to home screen
        if (userToken) navigate('/home')
    }, [navigate, userToken, success])
    
    return (
        <>
            <div className="container-sm container-register border rounded">
                <form className="m-2" onSubmit={handleSubmit(onSubmit)}>
                    <h3 className="text-center">Register</h3>
                    <hr/>
                    <div className="row mx-auto form-outline mb-3">
                        <div className="col-12">
                            <input type="email" className="form-control" placeholder="Email address..." {...register("email")}/>
                            { errors.email && <small style={{ color: "red" }}>{errors.email.message}</small> }
                        </div>
                    </div>
                    <div className="row mx-auto form-outline mb-3">
                        <div className="col-6">
                            <input type="text" className="form-control" placeholder="First Name..." {...register("firstName")}/>
                            { errors.firstName && <small style={{ color: "red" }}>{errors.firstName.message}</small> }
                        </div>
                        <div className="col-6">
                            <input type="text" className="form-control" placeholder="Last Name..." {...register("lastName")}/>
                            { errors.lastName && <small style={{ color: "red" }}>{errors.lastName.message}</small> }
                        </div>
                    </div>
                    <div className="row mx-auto form-outline mb-3">
                        <div className="col-6">
                            <input type="text" className="form-control" placeholder="Username..." {...register("username")}/>
                            { errors.username && <small style={{ color: "red" }}>{errors.username.message}</small> }
                        </div>
                        <div className="col-6">
                            <input type="password" className="form-control" placeholder="Password..." {...register("password")}/>
                            { errors.password && <small style={{ color: "red" }}>{errors.password.message}</small> }
                        </div>
                    </div>
                    <div className="row mx-auto form-outline mb-3">
                        <div className="col-6">
                            <input type="text" className="form-control" placeholder="Country..." {...register("country")}/>
                            { errors.country && <small style={{ color: "red" }}>{errors.country.message}</small> }
                        </div>
                        <div className="col-6">
                            <input type="text" className="form-control" placeholder="City..." {...register("city")}/>
                            { errors.city && <small style={{ color: "red" }}>{errors.city.message}</small> }
                        </div>
                    </div>
                    <div className="row mx-auto form-outline mb-3">
                        <div className="col-12">
                            <button type="submit" className="btn btn-primary" disabled={loading}>
                                { loading ? 'Submitting...' : 'Sign Up' }
                            </button>
                        </div>
                    </div>
                    <div className="row mx-auto form-outline mb-3">
                        <div className="col-12 text-center">Already a member? <Link to="/login">Login</Link></div>
                    </div>
                </form>
            </div>
            {
                error &&
                <div className="container-sm container-register mt-5">
                    <div className="alert alert-danger" role="alert">
                        <p className="mb-0">
                            Something went wrong, please try again.
                        </p>
                        <hr />
                        <p>{error}</p>
                    </div>
                </div>
            }
        </>
    )
}

export default Registration