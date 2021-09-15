select id,
       email,
       password
from user_table
where email = :email;