export class SessionSbService {
 RESOURCES_URL;
 BROWSER_STORAGE_ITEM_NAME;
 _currentToken;
 _currentAccount;
 _defaultAccountName = "Visitor";

 constructor(resourcesUrl, browserStorageItemName) {
  this.BROWSER_STORAGE_ITEM_NAME = browserStorageItemName;
  this.RESOURCES_URL = resourcesUrl;
  this._currentAccount = null;
  this._currentToken = null;

  this.getTokenFromBrowserStorage();
 }

 get currentAccount() {
   return this._currentAccount || this._defaultAccountName;
 }

 get currentToken() {
  return this._currentToken;
 }

 getTokenFromBrowserStorage() {
  if (this._currentToken != null) return this._currentToken
  this._currentToken = window.localStorage.getItem(this.BROWSER_STORAGE_ITEM_NAME);
  let jsonAccount = window.localStorage.getItem(this.BROWSER_STORAGE_ITEM_NAME+"_ACC");

  if (jsonAccount != null) {
   this._currentAccount = JSON.parse(jsonAccount);
  }
  return this._currentToken;
 }

 saveTokenIntoBrowserStorage(token, account) {
  this._currentToken = token;
  this._currentAccount = account;
  if (token == null) {
   this._currentAccount = null;
   window.localStorage.removeItem(this.BROWSER_STORAGE_ITEM_NAME);
   window.localStorage.removeItem(this.BROWSER_STORAGE_ITEM_NAME+"_ACC");
  } else {
   window.localStorage.setItem(this.BROWSER_STORAGE_ITEM_NAME, token);
   window.localStorage.setItem(this.BROWSER_STORAGE_ITEM_NAME+"_ACC", JSON.stringify(account));
  }
 }

 async asyncSignIn(email, password) {
  const body = JSON.stringify({ email: email, password: password });
  let response = await fetch(this.RESOURCES_URL + "/login",
      {
       method: 'POST',
       headers: { 'Content-Type': 'application/json' },
       body: body,
       credentials: 'include',
      })
  if (response.ok) {
   let user = await response.json();
   this.saveTokenIntoBrowserStorage(
       response.headers.get('Authorization'),
       user);
   return user;
  } else {
   alert("Something went wrong, please check your credentials again.")
   console.log(response)
   return null;
  }
 }

 signOut() {
  window.localStorage.removeItem(this.BROWSER_STORAGE_ITEM_NAME);
  window.localStorage.removeItem(this.BROWSER_STORAGE_ITEM_NAME+"_ACC");
  this._currentAccount = null;
  this._currentToken = null;
 }
}