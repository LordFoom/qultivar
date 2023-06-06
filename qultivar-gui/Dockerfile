# Base image
FROM node:19-alpine


# Set working directory
WORKDIR /app

# Copy package.json and package-lock.json
COPY package*.json ./

# copy the local node_modules folder to the container, to save the extra downloads when running npm install
# should probably take this out when building for production
COPY node_modules ./node_modules/

# Install dependencies
RUN npm install --production

# Copy application files
COPY . .

# Build React app
RUN npm run build

# Expose port
EXPOSE 3000

# Start the application
CMD ["npm", "start"]
