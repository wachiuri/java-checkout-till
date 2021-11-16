
class Till {

    constructor(pricingRules) {
        this.pricingRules = pricingRules;
        this.items = {};
        this.total = 0.00;
    }

    scan = (itemName) => {
        if (typeof this.items[itemName] !== "undefined") {
            this.items[itemName].count += 1;
        }
        else {
            this.items[itemName] = {
                count: 1,
                total: this.pricingRules[itemName].defaultPrice
            };
            this.total += this.pricingRules[itemName].defaultPrice;
            return this;
        }

        if (typeof this.pricingRules[itemName].rules === "undefined" ||
            (typeof this.pricingRules[itemName].rules.bulk === "undefined" &&
                typeof typeof this.pricingRules[itemName].rules.xfor1 === "undefined")
        ) {
            this.items[itemName].total = this.items[itemName].count * this.pricingRules[itemName].defaultPrice;
            this.total += this.items[itemName].total;
            return this;
        }

        if (typeof this.pricingRules[itemName].rules.bulk !== "undefined") {

            if (this.items[itemName].count >= this.pricingRules[itemName].rules.bulk.quantity) {
                let grossTotal = this.items[itemName].count
                    * this.pricingRules[itemName].defaultPrice;
                this.items[itemName].total = grossTotal
                    * ((100 - this.pricingRules[itemName].rules.bulk.discountPercentage) / 100);
            }
        }
        else if (typeof this.pricingRules[itemName].rules.xfor1 !== "undefined") {

            if (this.items[itemName].count >= this.pricingRules[itemName].rules.xfor1) {
                let payFor = Math.floor(this.items[itemName].count
                    / this.pricingRules[itemName].rules.xfor1);
                let remaining = this.items[itemName].count
                    % this.pricingRules[itemName].rules.xfor1;
                let payForAmount = payFor * this.pricingRules[itemName].defaultPrice;
                let remainingAmount = remaining * this.pricingRules[itemName].defaultPrice;

                this.items[itemName].total = payForAmount + remainingAmount;
            }
        }

        this.total = 0.00;
        for (let key in this.items) {
            this.total += this.items[key].total;
        }
        
        this.total = this.total.toFixed(2);

        return this;

    }
}

module.exports = Till;