SELECT text
FROM Stopword, Abstract
WHERE Stopword.stopwordId = Abstract.stopwordId AND counts < 15
