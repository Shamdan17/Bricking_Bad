# Contract: createAcc

**Operation:** createAcc(user: Username, password: Password)
**Cross references:** Use Cases: Create an Account
**Preconditions:** The user is on the account creation screen
**Postconditions:** 
* An *Account* instance is created 
* Account.user becomes Username
* Account.password becomes Password
* The account is stored in a database