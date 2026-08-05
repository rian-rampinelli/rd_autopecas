ALTER TABLE forma_pagamento
ALTER COLUMN status SET NOT NULL;

ALTER TABLE forma_pagamento
ADD CONSTRAINT uk_forma_pagamento_name UNIQUE (name);