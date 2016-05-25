SELECT Счет.Number, Счет.Date, Счет.Ссылка, Счет.Posted 
FROM Документ.СчетНаОплатуПокупателю Счет 
WHERE Счет.Number LIKE "119%"

 ИНТ00000001 Покупка, комиссия

select Number, Товары.КоличествоМест from Document.ПоступлениеТоваровУслуг WHERE Number = "ИНТ00000001";