
function convert(balance){
    balance = balance * (Mbalanceth.pow(10, -18));
    return(balance)
}

console.log(convert(14385800000000000))
