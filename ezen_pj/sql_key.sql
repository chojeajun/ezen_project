ALTER TABLE content
   ADD FOREIGN KEY (locationNum)
   REFERENCES locationNum (locationNum)
;
commit;