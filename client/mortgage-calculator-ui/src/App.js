import './App.css';
import {useEffect, useState} from "react";
import axios from "axios";

const App = () => {
  const [prospects, setProspects] = useState([]);
  const [customerName, setCustomerName] = useState("");
  const [totalLoanAmount, setTotalLoanAmount] = useState(0);
  const [yearlyInterest, setYearlyInterest] = useState(0);
  const [numberOfYears, setNumberOfYears] = useState(0);
  const [refreshProspects, setRefreshProspects] = useState(true);
  useEffect(() => {
    axios.get('/prospect')
        .then(response => response.data)
        .then(prospects => setProspects(prospects))
  }, [refreshProspects])

  const handleSubmit = (event) => {
    event.preventDefault();
    axios.post('/prospect', {
      customer: customerName,
      totalLoan: totalLoanAmount,
      yearlyInterestRate: yearlyInterest,
      numberOfYears: numberOfYears
    }).then(() => {
      setCustomerName("")
      setNumberOfYears(0)
      setYearlyInterest(0)
      setTotalLoanAmount(0)
    }).then(() => setRefreshProspects(prevState => !prevState));
  }
  return (
      <div className="App">
        <h1>Prospect Page</h1>
        <div style={{
          margin: "8% auto",
          width: "50%",
          boxShadow: "0 2px 16px rgba(0, 0, 0, 0.6)",
          background: "white",
          padding: "0px 0px 5px 15px",
          boxSizing: "border-box",
          borderRadius: "6px"
        }}>
          <form onSubmit={handleSubmit}>
            <div>
              <label>Prospect Name    </label>
              <input type="text" value={customerName}
                     onChange={e => setCustomerName(e.target.value)}/>
            </div>
            <div>
              <label>Total Loan Amount    </label>
              <input id="totalLoanAmount" type="number"
                     value={totalLoanAmount}
                     onChange={e => setTotalLoanAmount(e.target.value)}/>
            </div>
            <div>
              <label>Yearly Interest Rate    </label>
              <input id="yearlyInterest" type="number" value={yearlyInterest}
                     onChange={e => setYearlyInterest(e.target.value)}/>
            </div>
            <div>
              <label>Number of Years    </label>
              <input id="numberOfYears" type="number" value={numberOfYears}
                     onChange={e => setNumberOfYears(e.target.value)}/>
            </div>
            <div>
              <button type="submit">Submit</button>
            </div>
          </form>
        </div>

        <div>
          {prospects.map(({id, customer, totalLoan, numberOfYears, monthlyPayment}) => (
              <p key={id}>{customer} wants to borrow {totalLoan} € for a period of {numberOfYears} years and
                pay {monthlyPayment} € each month</p>
          ))}
        </div>
      </div>
  );
}

export default App;
