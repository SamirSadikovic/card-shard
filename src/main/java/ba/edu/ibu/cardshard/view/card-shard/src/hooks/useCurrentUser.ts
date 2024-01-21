import { useQuery } from "react-query";
import { UserService } from "../services";

const useCurrentUser = () => {
    return useQuery('currentUser',
        () => UserService.getCurrentUser()
    );
}


export default useCurrentUser;