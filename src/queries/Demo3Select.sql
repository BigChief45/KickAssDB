SELECT COUNT(*)
FROM Stopword, Abstract
WHERE Stopword.stopwordId = Abstract.stopwordId AND Stopword.freq > 17
