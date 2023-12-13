
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
        console.log("FetchInterceptor request: ", url, options, token);

        if (token == null) {
            return [url, options];
        } else if (options == null) {
            return [url, { headers: { Authorization: token }}]
        } else {
            let newOptions = { ...options };

            newOptions.headers.Authorization = `Bearer ${token}`;
            return [url, newOptions];
        }
    }

    response(response) {
        if (response.status >= 400 && response.status < 600) {
            FetchInterceptor.theInstance.handleErrorInResponse(response);
        }
        return response;
    }


    async handleErrorInResponse(response) {
        if (response.status == 401) {

            this.router.push({ path: '/sign-out',});
        }
    }

}