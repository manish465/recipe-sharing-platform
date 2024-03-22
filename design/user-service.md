# User Service
## Description
- This service is responsible for user management
## User Model
  | Attributes | Validation | Required | Note |
  | - | - | - | - |
  | First Name | 2-15 Chars | true | |
  | Last Name | 2-15 Chars | true | |
  | Email | email regexp and unique | true | |
  | Password 1 | 8-20 Chars | true | |
  | Password 2 | 8-20 Chars and Must be same as Password 1 | true | |

## Main Service Requirements
- [x] user can create a user
- [x] user can view one user by user id
- [x] user can view all user
- [x] user can delete one user by user id
- [x] delete all users
- [x] user can edit one user by user id
- [ ] get all recipes by current user
- [ ] get all products by current user
- [ ] get cart

## Notes