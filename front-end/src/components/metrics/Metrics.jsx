import React, { useState, useEffect} from "react";

import './Metrics.css';
import { SERVER_URL } from "../../constants";

function Metrics() {
    const [metrics, setMetrics] = useState([]);

    useEffect(() => {
        fetchMetrics();
    }, []);

    const fetchMetrics = () => {
        fetch(SERVER_URL + 'todos/metrics')
        .then(response => response.json())
        .then(data => setMetrics(data))
        .catch(err => console.error(err));
    }

    return(
        <>
            <div className="container">
                <div className="item">
                    <p>Average time to finish taks</p>
                    <p>{ metrics.general } minutes</p>
                </div>
                <div className="item">
                    <p>Average time to finish taks by priority</p>
                    <ul>
                        <li>Low: { metrics.low } minutes</li>
                        <li>Medium: { metrics.medium } minutes</li>
                        <li>High: { metrics.high } minutes</li>
                    </ul>
                </div>
            </div>
        </>
    );
}

export default Metrics;