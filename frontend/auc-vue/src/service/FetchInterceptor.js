
import fetchIntercept from 'fetch-intercept';

export class FetchInterceptor {
    static theInstance;
    session;
    router;
    unregister;

    constructor(session, router) {
        this.session = session;
        this.router = router;

        FetchInterceptor.theInstance = this;

        this.unregister = fetchIntercept.register(this);
        console.log(session.currentToken)


    }

    request(url, options) {
        let token = FetchInterceptor.theInstance.session.currentToken;
        //console.log("FetchInterceptor request: ", url, options, token);

        if (token == null) {
            return [url, options];
        } else if (options == null) {
            return [url, {headers: {Authorization: token}}]
        } else {
            let newOptions = { headers: { Authorization: token }, ... options};

            console.log("FetchInterceptor request: ", url, newOptions);
            return [url, newOptions];
        }
    }
    //
    // response(response) {
    //     // console.log("FetchInterceptor response: ", response);
    //     FetchInterceptor.theInstance.tryRecoverNewJWToken(response);
    //     if (response.status >= 400 && response.status < 600) {
    //         FetchInterceptor.theInstance.handleErrorInResponse(response);
    //     }
    //     return response;
    // }
    //
    // requestError(error) {
    //     // Called when an error occured during another 'request' interceptor call
    //     console.log("FetchInterceptor requestError: ", error);
    //     return Promise.reject(error);
    // }
    // responseError(error) {
    //     // Handle a fetch error
    //     console.log("FetchInterceptor responseError: ", error);
    //     return Promise.reject(error);
    // }
    //
    // async handleErrorInResponse(response) {
    //     if (response.status == 401) {
    //         // TODO handle an UNAUTHORISED response
    //         // unauthorised request, redirect to signIn page
    //         // this.router.navigate(['/sign-out']);    // ng-router
    //         this.router.push({ path: '/sign-out',});   // vue-router
    //     } else if (response.status != 406) {
    //         // 406='Not Acceptable' error is used for logon failure
    //         // TODO handle any other error
    //     }
    // }

}