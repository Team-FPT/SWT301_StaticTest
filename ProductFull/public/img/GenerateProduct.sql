create table Product(
	id int primary key identity(0,1),
	[name] nvarchar(225)  not null,
	[price] float not null,
	[image] nvarchar(225) not null
)
INSERT INTO Product ([name], [price],[image])
VALUES 
('Tivi HD Chat Luong Cao',2750000,'tivihd.jpg'),
('Tivi Toshiba Cao Cap',3266000,'toshiba.jpg'),
('Tivi chat luong 8k',1955000,'tivi8k.jpg'),
('Tivi LG Moi Nhat',5050000,'tivilg.png'),
('Tivi Sony',4230000,'sony.jpg'),
('Tivi Samsung',2390000,'samsung.jpg'),
('Tivi TCL',1999000,'tivitcl.jpg')

--tivi_sony_kd_75x85.jpg

select * from Product

drop table Product
--------------------------------------------------------------------------------------------------------

CREATE TABLE author (
    id_author INT PRIMARY KEY IDENTITY(1,1),
    name NVARCHAR(225) NOT NULL,
    dob DATE
);

CREATE TABLE book (
    id_book INT PRIMARY KEY IDENTITY(1,1),
    name NVARCHAR(225) NOT NULL,
    [year] DATE,
    id_author INT,
    FOREIGN KEY (id_author) REFERENCES author(id_author)
);
-- Chèn dữ liệu vào bảng author
INSERT INTO author (name, dob) VALUES ('Emily Brown', '1988-03-12');
INSERT INTO author (name, dob) VALUES ('Michael Johnson', '1975-09-25');
INSERT INTO author (name, dob) VALUES ('Sophia Lee', '1995-11-08');

-- Chèn dữ liệu vào bảng book
INSERT INTO book (name, [year], id_author) VALUES ('The Secret Garden', '1911-08-01', 3);
INSERT INTO book (name, [year], id_author) VALUES ('The Catcher in the Rye', '1951-07-16', 2);
INSERT INTO book (name, [year], id_author) VALUES ('To Kill a Mockingbird', '1960-07-11', 1);
INSERT INTO book (name, [year], id_author) VALUES ('Harry Potter and the Philosopher''s Stone', '1997-06-26', 1);
-- Chèn dữ liệu vào bảng book
INSERT INTO book (name, [year], id_author) VALUES ('Pride and Prejudice', '1813-01-28', 3);
INSERT INTO book (name, [year], id_author) VALUES ('1984', '1949-06-08', 2);
INSERT INTO book (name, [year], id_author) VALUES ('The Lord of the Rings', '1954-07-29', 2);
INSERT INTO book (name, [year], id_author) VALUES ('The Hobbit', '1937-09-21', 2);
INSERT INTO book (name, [year], id_author) VALUES ('Animal Farm', '1945-08-17', 2);
INSERT INTO book (name, [year], id_author) VALUES ('The Da Vinci Code', '2003-03-18', 1);
INSERT INTO book (name, [year], id_author) VALUES ('The Chronicles of Narnia', '1950-10-16', 3);
INSERT INTO book (name, [year], id_author) VALUES ('Gone with the Wind', '1936-06-30', 3);
INSERT INTO book (name, [year], id_author) VALUES ('The Alchemist', '1988-01-01', 1);
INSERT INTO book (name, [year], id_author) VALUES ('The Hunger Games', '2008-09-14', 3);


select * from author

select * from book

SELECT book.id_book, book.name, book.year, author.name AS Expr1, author.dob
FROM author INNER JOIN
book ON author.id_author = book.id_author
where author.id_author = 2

SELECT author.*
FROM author INNER JOIN
book ON author.id_author = book.id_author
where book.id_book = 2

drop table book
drop table author

