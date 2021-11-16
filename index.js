const Till = require("./Till");

let pricingRules = {
    TSHIRT: {
        defaultPrice: 21.00,
        rules: {
            bulk: {
                quantity: 3,
                discountPercentage: 30
            }
        }
    },
    MUG: {
        defaultPrice: 4.00,
        rules: {
            xfor1: 2
        }
    },
    USBKEY: {
        defaultPrice: 10.00,
        rules: {
        }
    }
};

let checkout = new Till(pricingRules)
.scan("MUG")
    .scan("USBKEY")
    .scan("TSHIRT");

let price = checkout.total;

console.log("items", checkout.items);
console.log("price", price);

checkout = new Till(pricingRules)
    .scan("MUG")
    .scan("TSHIRT")
    .scan("MUG");

price = checkout.total;

console.log("items", checkout.items);
console.log("price", price);

checkout = new Till(pricingRules)
    .scan("TSHIRT")
    .scan("TSHIRT")
    .scan("TSHIRT")
    .scan("MUG")
    .scan("TSHIRT")
    ;

price = checkout.total;

console.log("items", checkout.items);
console.log("price", price);

checkout = new Till(pricingRules)
    .scan("MUG")
    .scan("TSHIRT")
    .scan("MUG")
    .scan("MUG")
    .scan("USBKEY")
    .scan("TSHIRT")
    .scan("TSHIRT")
    ;

price = checkout.total;

console.log("items", checkout.items);
console.log("price", price);
