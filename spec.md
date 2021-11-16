## MingoButler (CDN) specification.

The CDN will store all sorts of files but for some files we will check for some specific parameters

For the image type of file (see list below) we'll allow relativeyly large images and video files (50 mb max).
If the image is above a max width or height of 1920x1080 then the CDN will automatically downscale it to that max size
Videos however will be blocked if above the maximum file size.

- jpeg
- jpg
- png
- heic

Flow.

Client makes an POST request to a MingoChan endpoint to get a JWT
that includes information such as ticketId, authorId, and messageId

Client makes a request towards the CDN that includes the file and the JWT as query parameter in a PUT request
CDN decodes the JWT if it isn't valid, has expired, or the user doesn't have the nececary permissions or is not the author of the ticket CDN returns 403.

CDN stores the folder named with the ticketId and the file is renamed to the messageId after processing (if needed).

In case that there are several files with the same message store it with a \_ with an appended integer. Then once the
CDN is done storing the files it responds with the ID of each file

Example folder

9df29b98-4a6a-4f45-8060-251c1163d81d:

--> 1c52461b-db8b-4a1f-8475-201821d26e1c

--> 1c52461b-db8b-4a1f-8475-201821d26e1c_1

--> 1c52461b-db8b-4a1f-8475-201821d26e1c_2

Each file should then be avalible at a URL like this:

https://cdn.mingodesk.com/file/1c52461b-db8b-4a1f-8475-201821d26e1c_2

However, the FE will apend a JWT like this: https://cdn.mingodesk.com/file/1c52461b-db8b-4a1f-8475-201821d26e1c_2?token=JWTHERE the CDN will then
check if the JWT is valid and if it isn't (it'll be relatively short-lived) then return a 403 otherwise respond
with the file
