import React, { useState, useEffect } from 'react';
import './App.css';

function App() {
  const [errors, setErrors] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    fetchErrors();
  }, []);

  const fetchErrors = async () => {
    try {
      const response = await fetch('http://localhost:8080/api/errors');
      const data = await response.json();
      setErrors(data);
      setLoading(false);
    } catch (error) {
      console.error('Error fetching:', error);
      setLoading(false);
    }
  };

  const resolveError = async (id) => {
    await fetch(`http://localhost:8080/api/errors/${id}/resolve`, {
      method: 'PUT'
    });
    fetchErrors();
  };

  const getSeverityColor = (severity) => {
    if (severity === 'HIGH') return '#ff4444';
    if (severity === 'MEDIUM') return '#ffaa00';
    return '#00cc44';
  };

  return (
    <div style={{ fontFamily: 'Arial', padding: '20px', backgroundColor: '#f5f5f5', minHeight: '100vh' }}>
      
      <div style={{ backgroundColor: '#1a1a2e', color: 'white', padding: '20px', borderRadius: '10px', marginBottom: '20px' }}>
        <h1 style={{ margin: 0 }}>🔍 Intelligent Error Monitor</h1>
        <p style={{ margin: '5px 0 0 0', color: '#aaa' }}>AI-powered error tracking and fix suggestions</p>
      </div>

      {loading ? (
        <p>Loading errors...</p>
      ) : (
        <div>
          <p style={{ color: '#666' }}>Total errors: {errors.length}</p>
          {errors.map(error => (
            <div key={error.id} style={{
              backgroundColor: 'white',
              borderRadius: '10px',
              padding: '20px',
              marginBottom: '15px',
              borderLeft: `5px solid ${getSeverityColor(error.severity)}`,
              boxShadow: '0 2px 5px rgba(0,0,0,0.1)'
            }}>
              <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
                <span style={{
                  backgroundColor: getSeverityColor(error.severity),
                  color: 'white',
                  padding: '3px 10px',
                  borderRadius: '20px',
                  fontSize: '12px',
                  fontWeight: 'bold'
                }}>{error.severity}</span>
                <span style={{
                  backgroundColor: error.status === 'RESOLVED' ? '#00cc44' : '#ff4444',
                  color: 'white',
                  padding: '3px 10px',
                  borderRadius: '20px',
                  fontSize: '12px'
                }}>{error.status}</span>
              </div>

              <h3 style={{ color: '#333', margin: '10px 0' }}>{error.message}</h3>
              
              <p style={{ color: '#666', fontSize: '13px' }}>
                🕒 {new Date(error.timestamp).toLocaleString()}
              </p>

              {error.aiSuggestion && (
                <div style={{ backgroundColor: '#f0f8ff', padding: '15px', borderRadius: '8px', margin: '10px 0' }}>
                  <strong>🤖 AI Fix Suggestion:</strong>
                  <p style={{ margin: '8px 0 0 0', color: '#333', whiteSpace: 'pre-wrap' }}>{error.aiSuggestion}</p>
                </div>
              )}

              {error.status === 'OPEN' && (
                <button
                  onClick={() => resolveError(error.id)}
                  style={{
                    backgroundColor: '#00cc44',
                    color: 'white',
                    border: 'none',
                    padding: '8px 20px',
                    borderRadius: '5px',
                    cursor: 'pointer',
                    marginTop: '10px',
                    fontWeight: 'bold'
                  }}>
                  ✅ Mark as Resolved
                </button>
              )}
            </div>
          ))}
        </div>
      )}
    </div>
  );
}

export default App;
