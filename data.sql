INSERT INTO pic_user (username, password)
VALUES ('alice', '123456'),
       ('bob', 'abcdef'),
       ('charlie', 'qwerty'),
       ('david', 'zxcvbn'),
       ('eve', 'asdfgh');

INSERT INTO pic_user_info (uid, name, age, info)
VALUES (1, 'Alice', 25, 'I love photography'),
       (2, 'Bob', 30, 'I am a professional photographer'),
       (3, 'Charlie', 28, 'I like to share my photos'),
       (4, 'David', 32, 'I am a fan of nature'),
       (5, 'Eve', 27, 'I enjoy taking selfies');


INSERT INTO pic_post_info (title, content, type, post_uid, post_name)
VALUES ('My latest work', 'This is a photo of a sunset I took yesterday. I hope you like it.', 'show', 2, 'Bob'),
       ('How to take better photos',
        'Here are some tips and tricks I learned from my experience. Feel free to comment and ask questions.', 'apo', 3,
        'Charlie'),
       ('A beautiful flower', 'I found this flower in the park today. It was so pretty and colorful.', 'show', 4,
        'David'),
       ('Selfie time', 'This is me at the beach. The weather was perfect and I had a lot of fun.', 'show', 5, 'Eve'),
       ('Need some advice', 'I want to buy a new camera but I dont know which one to choose. Any suggestions?', 'apo',
        1, 'Alice');

INSERT INTO pic_post_info_reply (pid, content, post_uid, post_name)
VALUES (1, 'Wow, thats amazing!', 1, 'Alice'),
       (1, 'Nice shot!', 4, 'David'),
       (2, 'Thanks for sharing!', 2, 'Bob'),
       (2, 'What kind of camera do you use?', 5, 'Eve'),
       (3, 'Beautiful!', 3, 'Charlie'),
       (3, 'What kind of flower is that?', 1, 'Alice'),
       (4, 'You look great!', 2, 'Bob'),
       (4, 'I wish I was there!', 3, 'Charlie'),
       (5, 'It depends on your budget and preference.', 2, 'Bob'),
       (5, 'I recommend the Canon EOS R6.', 4, 'David');