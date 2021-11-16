const { test } = require("@jest/globals");
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

test("total price is 35.00", () => {
    let checkout = new Till(pricingRules)
    .scan("MUG")
    .scan("USBKEY")
    .scan("TSHIRT");
    expect(checkout.total).toBe(35);
});



test("total price is 25.00", () => {
    let checkout = new Till(pricingRules)
    .scan("MUG")
    .scan("TSHIRT")
    .scan("MUG");
    expect(checkout.total).toBe("25.00");
});



test("total price is 62.80", () => {
    let checkout = new Till(pricingRules)
    .scan("TSHIRT")
    .scan("TSHIRT")
    .scan("TSHIRT")
    .scan("MUG")
    .scan("TSHIRT")
    ;
    expect(checkout.total).toBe("62.80");
});


test("total price is 62.10", () => {
    let checkout = new Till(pricingRules)
    .scan("MUG")
    .scan("TSHIRT")
    .scan("MUG")
    .scan("MUG")
    .scan("USBKEY")
    .scan("TSHIRT")
    .scan("TSHIRT")
    ;

    expect(checkout.total).toBe("62.10");
});