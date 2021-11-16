## MingoButler (CDN) specification.

The CDN will store all sorts of files but for some files we will check for some specific parameters

For the image type of file (see list below) we'll allow relativeyly large images and video files (50 mb max).
For all images that are 720p and above a thumbnail image is also to be created in the 360p.

- jpeg
- jpg
- png
- heic

Flow.

Client makes an POST request to a MingoChan endpoint to get a JWT
that includes information such as ticketId, authorId, and messageId

Client makes a request towards the CDN that includes the file and the JWT as query parameter in a PUT request
CDN decodes the JWT if it isn't valid, has expired, or the user doesn't have the necessary permissions or is not
the author of the ticket CDN returns 403.

CDN stores the folder named with the ticketId and the file is renamed to the messageId after processing (if needed).

Each file must have an _+int so first file would be filename_0 then file two would be filename_2, etc.

Each file must be encrypted.

Example folder

9df29b98-4a6a-4f45-8060-251c1163d81d:

--> 1c52461b-db8b-4a1f-8475-201821d26e1c_0_original.jpg

--> 1c52461b-db8b-4a1f-8475-201821d26e1c_0_small.jpg

--> 1c52461b-db8b-4a1f-8475-201821d26e1c_1

--> 1c52461b-db8b-4a1f-8475-201821d26e1c_2

Each file should then be avalible at a URL like this:

https://cdn.mingodesk.com/file/1c52461b-db8b-4a1f-8475-201821d26e1c_2

However, the FE will apend a JWT like this: https://cdn.mingodesk.com/file/1c52461b-db8b-4a1f-8475-201821d26e1c_2?token=JWTHERE the CDN will then
check if the JWT is valid and if it isn't (it'll be relatively short-lived) then return a 403 otherwise respond
with the file
